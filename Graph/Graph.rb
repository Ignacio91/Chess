=begin
  * Graph
  * Description :handles all operation done on the graph
  * Author:Ignacio Ferrero
=end
require "rubygems"
require_relative 'AddInfo.rb'

class Graph
  attr_accessor :parse
  @parse = {}
  def initialize(graph_parse)
    @parse = graph_parse
  end
  def addNode
    hash = AddInfo.new.getInformation
    puts hash
    @parse.push(hash)
  end
  def addRoute(csa)
    @parse.map{ |airport| 
               if( airport["name"] == csa)
                  destination = AddInfo.new.RouteMenu()
                  airport["destinations"].push(destination)
               end
    }
  end
  def deleteNode(csa)
        @parse.map{ |airport| 
                       if( airport["name"] == csa)
                          correctRoutes(airport["code"])
                         @parse.delete(airport)
                       end
        }
  end
  def deleteRoute(csa)
    @parse.map{ |airport| 
                   if( airport["name"] == csa)
                     removeRoute(airport["destinations"])
                   end
    }
  end
  def removeRoute(destination)
    hash = AddInfo.new.introduceCode()
    destination.map{ |dest| 
    if(dest["ports"].include?(hash[0]) and dest["ports"].include?(hash[1]) )
      destination.delete(dest)
    end
    }
  end

  def correctRoutes(code)
    @parse.map{ |airport| 
      airport["destinations"].map{ |dest|
        puts code
        puts dest["ports"].to_s
        if(dest["ports"].include?(code))
          puts "oa"
          airport["destinations"].delete(dest)
        end
      }
    }
  end
  def editCity(csa, operation, new_parameter)
    if(operation == "population" or operation == "timezone" )
      new_parameter.to_i
    end
      
    @parse.map{ |airport|
      if( airport["name"] == csa)
        airport[operation] = new_parameter
      end
    }
  end
  
  
end