// import Mill dependency
import mill._
import mill.define.Sources
import mill.modules.Util
import mill.scalalib.TestModule.ScalaTest
import scalalib._
// support BSP
import mill.bsp._

object ivys {
  val sv = "2.13.8"
  val chisel3 = ivy"edu.berkeley.cs::chisel3:3.5.5"
  val chisel3Plugin = ivy"edu.berkeley.cs:::chisel3-plugin:3.5.6"
  val chiseltest = ivy"edu.berkeley.cs::chiseltest:0.5.6"
  val chiselCirct = ivy"com.sifive::chisel-circt:0.8.0"
  val scalatest = ivy"org.scalatest::scalatest:3.2.14"
  val scalacOptions = Seq(
    "-language:reflectiveCalls",
    "-deprecation",
    "-feature",
    "-Xcheckinit",
    "-P:chiselplugin:genBundleElements"
  )
}


object Test extends SbtModule { m =>
  override def millSourcePath = os.pwd

  override def scalaVersion = ivys.sv

  override def scalacOptions = ivys.scalacOptions 
  
  override def ivyDeps = Agg(ivys.chisel3) ++ Agg(ivys.chiselCirct)
  

  override def scalacPluginIvyDeps = Agg(ivys.chisel3Plugin) 
  object test extends Tests with ScalaTest {
    override def ivyDeps = m.ivyDeps() ++ Agg(ivys.chiseltest)
  }
}
