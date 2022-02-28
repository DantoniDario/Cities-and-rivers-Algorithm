import java.net._
import scala.io.StdIn
object Project2Bonus_Dario_Dantoni extends App {
  // function to find all the rivers in the city r
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
  // function to find all the lakes in the city r
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
  // function to find all the rivers which flow on river r
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
  // function to find all the rivers which flow on lake r
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
  // function to find all the lakes which flow on river r
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
  // function to check if the river r which flows on a lake, flows also in a sea
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
  // function to check if the river r which flows on a lake, flows also in a river
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
  // function to find all the cities around river r
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
  // function to find all the cities around lake r
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
  var n = StdIn.readLine("Introduce the name of the city:  ").toString // variable to save the name of city written by the user
  var rivers = Set[String]() // variable to save all the discovered rivers and allow me to print them at the end
  var lakes = Set[String]() // variable to save all the discovered lakes and allow me to print them at the end
  if(riverOnCity(n)!=Set("")) // if there is a river around the city written by the user..
    rivers = riverOnCity(n) //..save this river
  if(lakeOnCity(n)!=Set("")) // if there is a lake around the city written by the user..
    lakes = lakeOnCity(n) //..save this lake
  var stop = false // variable which stop the loop when there will be no further discovered rivers anymore
  var waters = Set[String]() // variable create just to allow me to print all the discovered rivers and lakes together
  var old_rivers = Set[String]() // variable which allows me to use the rivers inside it to find new rivers/lakes which flow in those
  var old_lakes = Set[String]() // variable which allows me to use the lakes inside it to find new rivers which flow in those
  var discovered_rivers = Set[String]() // variable which contains the new discovered rivers which flow in "old_rivers" or "old_lakes"
  var discovered_lakes = Set[String]() // variable which contains the new discovered lakes which flow in "old_rivers"
  var check_river = Set[String]() // variable which will contain the rivers to be checked if their final destination is a lake
  old_rivers = rivers // old_rivers retrieves the new discovered rivers for the next while loop
  old_lakes = lakes // old_lakes retrieves the new discovered lakes for the next while loop
  if (rivers == Set() && lakes == Set()) { // if there is no river or lake around the city written by the user or the name was not well written..
    println("") //..print ""..
    println("There is no river either lake around this city or the name of the city is not correct") //..and print..
    stop = true // avoid to go in the loop since we don't have neither discovered rivers either lakes
  }
  while(stop !=true){// while there are new discovered rivers or lakes
    var cities = Set[String]() // variable which will receive the cities around the discovered rivers
    for (i <- old_rivers) { // for each discovered rivers..
      cities ++= citiesOnRiver(i) //..find cities around it and save them on "cities"
    }
    for (i <- old_lakes) { // for each discovered lakes..
      cities ++= citiesOnLake(i) //..find cities around it and save them on "cities"
    }
    if(cities!=Set()) { //if there is at least one city around the discovered rivers..
      println("") //..print ""
      waters ++= old_lakes // "waters" retrieve the new discovered lakes
      waters ++= old_rivers // "waters" retrieve the new discovered rivers
      println("Cities around " + waters.mkString(", ") + " :") // print "Cities around " and all the discovered rivers and lakes together
      println("-->  " + cities.mkString(", ")) //..and print just below the cities around these rivers and lakes just above
      waters = Set() // empty the variable "waters" for the next loop while
    }
    discovered_rivers =Set() // empty the variable from the old discovered rivers for the for loop just below
    discovered_lakes =Set() // empty the variable from the old discovered lakes for the for loop just below
    check_river =Set() // empty the variable from the rivers having been already checked for the for loop just below
    for (i <- old_rivers ) { // for each old discovered rivers..
      discovered_rivers ++= riversOnRiver(i) //..find the rivers which flow in it and save them in "discovered_rivers"
      discovered_lakes ++= lakeOnRiver(i) //..find the lakes which flow in it and save them in "discovered_lakes"
    }
    for (i <- old_lakes ) { // for each old discovered lakes..
      check_river ++= riverOnLake(i) //..find the rivers which flow in it and save them in "check_river"
    }
    for (i <- check_river){ // for each rivers to be checked (those which flow in a lake)..
      if (checkriversOnSea(i) == Set("") && checkriversOnRiver(i) == Set("")) // if the river doesn't flow either on sea neither on river..
        discovered_rivers += i //.. save them in "discovered_rivers" --> this means they have a lake as a final destination and thus there a connection between them
    }
    old_rivers = discovered_rivers // old_rivers retrieves the new discovered rivers for the next while loop
    old_lakes = discovered_lakes // old_lakes retrieves the new discovered lakes for the next while loop

    if (discovered_rivers == Set() && discovered_lakes == Set()){ // if there is no either new discovered rivers neither lakes anymore..
      stop = true //..set up the variable "stop" as true to stop the while loop
    }
    else { // else..
      rivers ++= old_rivers //..save the new discovered rivers in "rivers" (which can not contain twice the same rivers)
      lakes ++= old_lakes //..save the new discovered lakes in "lakes" (which can not contain twice the same lakes)
    }
  }
  if (rivers != Set() && lakes != Set()) { // if we discovered any river or lake from the first city written by the user..
    println("") // print ""..
    println("Discovered lakes:") // and print "Discovered lakes:"
    println(lakes.mkString(", ")) // and print the names of all the discovered lakes
    println("") // and print ""..
    println("Discovered rivers:") // and print "Discovered rivers:"
    println(rivers.mkString(", ")) // and print the names of all the discovered rivers
  }
}
