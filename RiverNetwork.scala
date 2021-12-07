import java.net._
import scala.io.StdIn
object Copie_project2Bonus extends App {
  // a method to find all the cities on river r
  def riverOnCity(r: String): Set[String] = {
    val q = "select River from located where City = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  def lakeOnCity(r: String): Set[String] = {
    val q = "select Lake from located where City = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  // a method to find all the cities on river r
  def riversOnRiver(r: String): Set[String] = {
    val q = "select Name from river where River = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  def riverOnLake(r: String): Set[String] = {
    val q = "select Name from river where Lake = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  def lakeOnRiver(r: String): Set[String] = {
    val q = "select Name from lake where River = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  def checkriversOnSea(r: String): Set[String] = {
    val q = "select Sea from river where Name = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  def checkriversOnRiver(r: String): Set[String] = {
    val q = "select River from river where Name = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  // a method to find all the cities on river r
  def citiesOnRiver(r: String): Set[String] = {
    val q = "select City from located where River = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  def citiesOnLake(r: String): Set[String] = {
    val q = "select City from located where Lake = '" + r + "'"
    val eq = URLEncoder.encode(q, "UTF-8")
    val u = new java.net.URL(
      "http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(u, "iso-8859-1")
    var res = Set[String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res += cols(0)
    }
    in.close()
    res
  }
  var n = StdIn.readLine("Introduce the name of the city:  ").toString
  var rivers = Set[String]()
  var lakes = Set[String]()
  if(riverOnCity(n)!=Set(""))
    rivers = riverOnCity(n)
  if(lakeOnCity(n)!=Set(""))
    lakes = lakeOnCity(n)
  var stop = false
  var count = 1
  var old_rivers = Set[String]()
  var old_lakes = Set[String]()
  var discovered_rivers = Set[String]()
  var discovered_lakes = Set[String]()
  var check_river = Set[String]()
  old_rivers = rivers
  old_lakes = lakes
  if (rivers == Set() && lakes == Set()) {
    println("")
    println("There is no river either lake around this city or the name of the city is not correct")
    stop = true
  }
  while(stop !=true){
    var cities = Set[String]()
    for (i <- old_rivers) {
      cities ++= citiesOnRiver(i)
    }
    for (i <- old_lakes) {
      cities ++= citiesOnLake(i)
    }
    if(cities!=Set()) {
      println("")
      println("Cities n°"+count)
      println(cities.mkString(", "))
    }
    discovered_rivers =Set()
    discovered_lakes =Set()
    check_river =Set()
    for (i <- old_rivers ) {
      discovered_rivers ++= riversOnRiver(i)
      discovered_lakes ++= lakeOnRiver(i)
    }
    for (i <- old_lakes ) {
      check_river ++= riverOnLake(i)
    }
    for (i <- check_river){
      if (checkriversOnSea(i) == Set("") && checkriversOnRiver(i) == Set(""))
        discovered_rivers += i
    }
    old_rivers = discovered_rivers
    old_lakes = discovered_lakes

    if (discovered_rivers == Set() && discovered_lakes == Set()){
      stop = true
    }
    else {
      rivers ++= old_rivers
      lakes ++= old_lakes
    }
    if (cities != Set())
      count += 1
  }
  if (rivers != Set() && lakes != Set()) {
    println("")
    println("Discovered lakes:")
    println(lakes.mkString(", "))
    println("")
    println("Discovered rivers:")
    println(rivers.mkString(", "))
  }
}
