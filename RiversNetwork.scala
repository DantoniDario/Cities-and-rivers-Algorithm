import java.net._
import scala.io.StdIn
object Project2_Dario_Dantoni extends App {
  // function to find all the rivers in the city r
  def riverOnCity(r: String): Set[String] = {
    val q = "select river from located where city = '" + r + "'"
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
    val q = "select Name from river where river = '" + r + "'"
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
    val q = "select city from located where river = '" + r + "'"
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
  if(riverOnCity(n)!=Set("")) // if there is a river around the city written by the user..
    rivers = riverOnCity(n) //..save this river
  var stop = false // variable which stop the loop when there will be no further discovered rivers anymore
  var discovered_rivers = Set[String]() // variable which contains the new discovered rivers which flow in "old_rivers"
  var old_rivers = Set[String]() // variable which allows me to use the rivers inside it to find new rivers which flow in those
  old_rivers = rivers // old_rivers retrieves the new discovered rivers for the next while loop
  if (rivers == Set()) { // if there is no river around the city written by the user or the name was not well written..
    println("") //..print ""..
    println("There is no river around this city or the name of the city is not correct") //..and print..
    stop = true // avoid to go in the loop since we don't have discovered rivers
  }
  while(stop !=true){ // while there are new discovered rivers
    var cities = Set[String]() // variable which will receive the cities around the discovered rivers
    for (i <- old_rivers) { // for each discovered rivers..
      cities ++= citiesOnRiver(i) //..find cities around it and save them on "cities"
    }
    if(cities!=Set()) { //if there is at least one city around the discovered rivers..
      println("") //..print ""
      println("Cities around "+ old_rivers.mkString(", ") + " :") //..and print "Cities around " and the discovered rivers..
      println("-->  " + cities.mkString(", ")) //..and print the cities around the discovered rivers
    }
    discovered_rivers =Set() // empty the variable from the old discovered rivers for the for loop just below
    for (i <- old_rivers ) { // for each old discovered rivers..
      discovered_rivers ++= riversOnRiver(i) //..find the rivers which flow in it and save them in "discovered_rivers"
    }
    old_rivers = discovered_rivers // old_rivers retrieves the new discovered rivers for the next while loop


    if (discovered_rivers == Set()){ // if there is no new discovered rivers anymore..
      stop = true //..set up the variable "stop" as true to stop the while loop
    }
    else { // else..
      rivers ++= old_rivers //..save the new discovered rivers in "rivers" (which can not contain twice the same rivers)
    }
  }
  if (rivers != Set()) { // if we discovered any river from the first city written by the user..
    println("") // print ""..
    println("Discovered rivers:") // and print "Discovered rivers:"
    println(rivers.mkString(", ")) // and print the names of all the discovered rivers
  }
}
