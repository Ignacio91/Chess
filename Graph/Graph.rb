=begin
  * Graph
  * Description :handles all operation done on the graph
  * Author:Ignacio Ferrero
=end
require "rubygems"
require "json"


class Graph
  
  @@parse = {}
  def initialize(graph_parse)
    @@parse = graph_parse
  end
  def addNode
    hash = AddInfo.new.getInformation
    grahparse.push(hash)
  end
  def addRoute(csa)
    @@parse.map{ |airport| 
               if( airport["name"] == csa)
                  destination = AddInfo.new.RouteMenu()
                  airport["destination"].push(destination)
               end
    }
  end
  def deleteNode(csa)
        @@parse.map{ |airport| 
                       if( airport["name"] == csa)
                          CorrectRoutes(airport["Code"])
                           parse.delete(airport)
                       end
        }
  end
  def deleteRoute(csa)
    @@parse.map{ |airport| 
                   if( airport["name"] == csa)
                     removeRoute(airport["destination"])
                   end
    }
  end
  def removeRoute(destination)
    hash = AddInfo.new.IntroduceCode()
    destination.map{ |dest| 
    if(dest["ports"].include?(hash[0]) and dest["ports"].include?(hash[1]) )
      destination.delete(dest)
    end
    }
  end

  def CorrectRoutes(code)
    @@parse.map{ |airport| 
      airport["destinations"].map{ |dest|
        if(dest["ports"].include?(code))
          airport["destinations"].delete(dest)
        end
      }
    }
  end
  def editCity(csa, operation, new_parameter)
    if(operation == "population" or operation == "timezone" )
      operation.to_i
    end
      
    @@parse.map{ |airport|
      if( airport["name"] == csa)
        airport[operation] = new_parameter
      end
    }
  end
  
  
end