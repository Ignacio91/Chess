=begin
  * GraphClass
  * Description :Gets all the info needed to create a Graph
  * Author:Ignacio Ferrero
=end
require_relative 'Manager.rb'
=begin
  * Gets all the info name, code country etc
=end
class AddInfo
  def getInformation
    puts "Creating Node : "
    
    code = check(3, "code")#checks if the input is correct
    name = getBasic("Name")
    country = check(2, "country")
    continent = getBasic("Continent")
    timezone = getBasic("TimeZone").to_i#convert to int 
    hash_coordinates = getCoordinates()
    population = getBasic("Population").to_i
    region = getBasic("Region").to_i
    
    hash_Routes = RouteMenu() #get all the routes you want to add
     
     hash =createHash(code, name, country, continent, timezone, hash_coordinates, population, region, hash_Routes)
     return hash
  end
=begin
    * Checks if the input is correct for example Code has to be 3 letters
=end
  def check(length, operation)
    puts "Introduce a "+ operation + " :"
    code = gets
    code.delete!("\n")
    until code.length == length
        puts "Invalid code" + length.to_s + " Capital letters"
        code = gets
        code.delete!("\n")#always delete /n chomp will also work but much more ineffcient
    end
    return code
  end
=begin
      * Function that gets the input --- refactoring
=end
  def getBasic(operation)
    puts "Introduce a " +operation
    return gets.delete!("\n")
  end
=begin
      * Gets the coordinates HASH
=end
  def getCoordinates()
    puts "Introduce Latitude : "
    latitude = gets.to_i
    puts "Introduce Longitude : "
    longitude = gets.to_i
    
    hash = { "S"=>latitude, "W"=>longitude}
    return hash
  end
=begin
      * Function to introduce new Routes to the existing node
=end
  def introduceRoutes()

    code1  = check(3, "Code1")
    code2  = check(3, "Code2")
    
    distance = getBasic("Distance").to_i
    
    destination = {"ports" => [code1, code2], "distance" => distance}
    return destination
    
  end
=begin
    * Gets users input and saves into the node
=end
  def RouteMenu()
    destination = []
    manager = Manager.new
    option  = 1
    until option == 2#normally you add one rute there is the option to add more if you want
      destination.push(introduceRoutes())
      option = manager.getRoutMenu
    end
    return destination

  end
=begin
    * Template for adding a node after you have all the information
=end
  def createHash(code, name, country, continent,timezone, coordinates, population,region, destinations)
    hash = {
      "code" => code,
      "name" => name,
      "country" => country,
      "continent" => continent,
      "timezone" => timezone,
      "coordinates" => coordinates,
      "population" => population,
      "region" => region,
      "destinations" => destinations,
    }
    return hash
  end
=begin
    * Introduce Code decrepicated
=end
  def introduceCode()
    code1 =  code = check(3, "Code1")
    code2 =  code = check(3, "Code2")
    return [code1, code2]
  end
end