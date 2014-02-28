
require_relative 'Manager.rb'
class AddInfo
  def getInformation
    puts "Creating Node : "
    
    code = check(3, "code")
    name = getBasic("Name").to_i
    country = check(2, "country")
    continent = getBasic("Continent").to_i
    timezone = gets.getBasic("TimeZone").to_i
    hash_coordinates = getCoordinates()
    population = getBasic("Population").to_i
    region = getBasic("Region").to_i
    
    hash_Routes = RouteMenu()
     
     hash =createHash(code, name, country, continent, timezone, hash_coordinates, population, region, hash_Routes)
     return hash
  end
  def check(length, operation)
    puts "Introduce a "+ operation + " :"
    code = gets
    code.delete!("\n")
    until code.length == length
        puts "Invalid code" + length.to_s + " Capital letters"
        code = gets
        code.delete!("\n")
    end
    return code
  end
  def getBasic(operation)
    puts "Introduce a " +operation
    return gets
  end
  def getCoordinate()
    puts "Introduce Latitude : "
    latitude = gets.to_i
    puts "Introduce Longitude : "
    longitude = gets.to_i
    
    hash = {"coordinates" => { "S"=>latitude, "W"=>longitude}}
    return hash
  end
  def introduceRoutes()

    code1  = check(3, "Code1")
    code2  = check(3, "Code2")
    
    distance = getBasic("Distance").to_i
    
    destination = [{"ports" => [code1, code2], "distance" => distance}]
    return destination
    
  end
  def RouteMenu()
    destination = []
    manager = Manager.new
     option  = manager.gerRoutMenu
    until option == 2
      manager.gerRoutMenu
      destination.push(introduceRoute())
    end
    return destination

  end
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
  def introduceCode()
    code1 =  code = check(3, "Code1")
    code2 =  code = check(3, "Code2")
    return [code1, code2]
  end
end