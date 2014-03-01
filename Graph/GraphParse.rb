require "rubygems"
require "json"
require_relative 'CostTimeCalculation.rb'
=begin
  * GraphCLASS
  * Description :Class to parse the json and generate the graph(Hashes)
  * Author:Ignacio Ferrero
=end

class GraphParse
  attr_accessor :parse_km, :parse_fligth, :parse_time
=begin
    * parse the JSON Object
=end
 
  def parseFile(file)
    json = file.read
    return JSON.parse(json)
  end
=begin
    * parse the string json into a hash
=end
 
  def create(parse)
      graph = []
      metros = parse["metros"]
      metros.map{ |airport|
        #looks for all the routes available to add them into the node = Vertex
        graph.push(airport.merge(getdestinitation(parse, airport["code"])))
      }
      p graph
      return graph
    end
=begin
      * Function gets all the destination for the grap within one fligth
=end
    def getdestinitation(parse, code)
      destination = []
      routes = parse["routes"]
      routes.map{ |airport|
        if(airport["ports"].include?(code))#the code is in the  ports
          destination.push(airport)
        end
        }
        #Generate HASH for destination
       h = {"destinations"=> destination}
      return h
    end
    def parseRoutes(parse)
      parses_km = []
      parses_fligth = []
      parses_time = []
      cost = CostTimeCalculation.new
      routes = parse["routes"]
              routes.map{ |airport|
              array_km = [airport["ports"][0] , airport["ports"][1] , airport["distance"]]
              array_fligth = [airport["ports"][0] , airport["ports"][1] , cost.getCost(airport["distance"])]
              array_time = [airport["ports"][0] , airport["ports"][1] , cost.getTime(airport["distance"])]  
              
              parses_km.push(array_km)
              parses_fligth.push(array_fligth)
              parses_time.push(array_time)
              
              }
              @parse_km = parses_km
              @parse_fligth = parses_fligth
              @parse_time = parses_time 
              
              
              
    end
  def merge(parsed, new_parsed)
    new_parsed["metros"].map{ |airport|
          parsed["metros"].push(airport)
        }
    new_parsed["routes"].map{ |airport|
      parsed["routes"].push(airport)
    }

          
      
    graph_parsed = create(parsed)  
    graph= Graph.new(graph_parsed)
    
    return parsed, graph_parsed , graph
      
  end
    
    
    def updateDisk(csa)
      File.open("map-data1.json","w") do |f|
      f.write(JSON.pretty_generate(csa))
      end
    end

end