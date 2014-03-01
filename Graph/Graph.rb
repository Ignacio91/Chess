=begin
  * Graph
  * Description :Handles all operation done on the graph
  * Author:Ignacio Ferrero
=end
require "rubygems"
require_relative 'AddInfo.rb'

class Graph
  #Global variable with all the information :nodes and vertex
  attr_accessor :parse
  @parse = {}
    
  def initialize(graph_parse)
    @parse = graph_parse
  end
=begin
    * Function to add a city to the airline
=end
  def addNode
    hash = AddInfo.new.getInformation
    puts hash
    @parse.push(hash)
  end
=begin
    * Function to add a Route / vertex to an existing city
=end
  def addRoute(csa)
    @parse.map{ |airport| 
               if( airport["name"] == csa)#Found the city add the route
                  destination = AddInfo.new.RouteMenu()
                  airport["destinations"].push(destination)
               end
    }
  end
=begin
    * Function to delete an actual city
=end
  def deleteNode(csa)
        @parse.map{ |airport| 
                       if( airport["name"] == csa)#Found the city delete it
                          correctRoutes(airport["code"])#Correct all other cities to eliminate the city from their routes
                         @parse.delete(airport)#Finally delete
                       end
        }
  end
=begin
    * Function to delete an actual route of the city network
=end
  def deleteRoute(csa)
    @parse.map{ |airport| 
                   if( airport["name"] == csa)#City found delete
                     removeRoute(airport["destinations"])
                   end
    }
  end
=begin
      * Function to delete the route from the other city
=end
  def removeRoute(destination)
    hash = AddInfo.new.introduceCode()
    destination.map{ |dest| 
    if(dest["ports"].include?(hash[0]) and dest["ports"].include?(hash[1]) )
      destination.delete(dest)
    end
    }
  end
=begin
    * Function to delete the city connection from all the possible routes
=end
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
=begin
    * Function to edit the existing information of the city
    * gets which field has to be modified = operation
=end
  def editCity(csa, operation, new_parameter)
    if(operation == "population" or operation == "timezone" )#if the parameter is int convert
      new_parameter.to_i
    end
    @parse.map{ |airport|
      if( airport["name"] == csa)#edit parameter
        airport[operation] = new_parameter
      end
    }
  end
  
  
end