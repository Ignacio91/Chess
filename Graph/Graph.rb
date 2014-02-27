=begin
  * Graph
  * Description :handles all operation done on the graph
  * Author:Ignacio Ferrero
=end
require "rubygems"
require "json"

class Graph
  def initialize()
  end
  def create(parse)
    p parse["metros"]
    graph = []
     metros = parse["metros"]
    metros.map{ |airport|
      graph.push(airport.merge(getdestinitation(parse, airport["code"])))
    }
    p graph
    return graph
  end
  def getdestinitation(parse, code)
    destination = []
    routes = parse["routes"]
    routes.map{ |airport|
      if(airport["ports"].include?(code))
        destination.push(airport)
      end
      }
     h = {"destinations"=> destination}
    return h
  end
  def getOperation(csa, operation ,graph)
    puts "CSAIR Codes of" 
    graph.map{ |airport| 
      if( airport["name"] == csa) 
        puts "The "+ operation +" is " +airport[operation]
      end
  }
end
  def getCities(csa)
      puts "CSAIR Codes of" + csa
      graph.map{ |airport| 
        puts
      }

      
  end
  def getList()
      puts "CSAIR Names of cities :"
      graph.map{ |airport| puts airport["names"] }
        
    end
  def getLongFlight(csa, graph)
    number2 = 0
    graph.map{ |airport| 
          if( airport["name"] == csa) 
            airport["destinations"].map do |element|
               number1 = element["distance"]
                 if(number1>number2)
                   number2 = number1
                 end
            end
            airport["destinations"].map{ |dest| 
              if(number2==dest["distance"])
                puts "The max distance is  " + number2.to_s + " in the fligth between "+ dest["ports"].to_s
              end
            }
          end
    }
  end
def getShortFlight(csa, graph)
    number2 = 10000000
    graph.map{ |airport| 
          if( airport["name"] == csa) 
            airport["destinations"].map do |element|
               number1 = element["distance"]
                 if(number1<number2)
                   number2 = number1
                 end
            end
            airport["destinations"].map{ |dest| 
              if(number2==dest["distance"])
                puts "The min distance is  " + number2.to_s + " in the fligth between "+ dest["ports"].to_s
              end
            }
          end
    }
  end
def getAverage(csa, graph)
  number1=0
    graph.map{ |airport| 
          if( airport["name"] == csa) 
            airport["destinations"].map do |element,i|
              number1 += element["distance"]
            end
            puts "The average is : " + number1/i
          end
            
    }
  end
def getMaxPopulation(csa, graph)
  population1 = 0
    graph.map{ |airport| 
          if( airport["name"] == csa) 
            airport["destinations"].map do |element,i|
              code = getCode(element["ports"], airport["code"])
              population2 = getPopulation(code, graph) 
              if(population2>population1)
                population1 = population2
              end
            end
            
            puts "the city with Min population available  is : " + getPopulationCity(population1, graph)
          end
            
    }
  end
def getAverage(csa, graph)
  population1 = 0
    graph.map{ |airport| 
          if( airport["name"] == csa) 
            airport["destinations"].map do |element, i|
              code = getCode(element["ports"], airport["code"])
              population1 += getPopulation(code, graph) 
              end
            puts "The average population  is : " + population1 /i
            end
            
            
    }
  end
def getMinPopulation(csa, graph)
  population1 = 10000000000
    graph.map{ |airport| 
          if( airport["name"] == csa) 
            airport["destinations"].map do |element,i|
              code = getCode(element["ports"], airport["code"])
              population2 = getPopulation(code, graph) 
              if(population2<population1)
                population1 = population2
              end
            end
            
            puts "The City with Min population is : " + getPopulationCity(population1, graph)
          end
            
    }
  end
  def getPopulation(code, graph)
    graph.map{ |airport| 
              if( airport["code"] == code) 
                return airport["population"]
              end
    }
  end
    def getPopulationCity(code, graph)
        graph.map{ |airport| 
                  if( airport["population"] == code) 
                    return airport["name"]
                  end
        }
  end
  
  def getCode(code1, code2)
    if(code1[1] != code2)
      return code1[0]
    else
       return code1[1]
      end   
  end
  
  def getListofContinents(csa)
    end
  def getHubCity(csa)
    return 2
  end
  
end