=begin
  * Script for the application
=end

require "rubygems"
require "json"
require_relative 'Graph.rb'
require_relative 'Manager.rb'
require_relative 'ManageSimple.rb'
require_relative 'ManageStatic.rb'
require_relative 'ManageRoutes.rb'
require_relative 'ManageList.rb'
require_relative 'GraphUtilities.rb'
require_relative 'GraphParse'
require_relative 'ManageRoutesModify.rb'
require_relative 'ManageStatistic.rb'

manager = Manager.new
manager.welcome
#opens the json file

#parse the json into a grpah / hash
#graph = Graph.new()

gp = GraphParse.new
#opens the json file
file = open("map_data.json")
parsed = gp.parseFile(file)
#parse the json
graph_parsed = gp.create(parsed)
#create the graph
graph= Graph.new(graph_parsed)


csa = manager.getInfo
csa.delete!("\n")
puts csa
option = 2
until csa == "X"#Exit option
    until option == 10#Exit option
      option = manager.getRealInformation(csa)
      if option == 1
            option_simple = 11
            until option_simple == 10#Exit option
              option_simple = manager.putOptionsSimple
              manage_simple= ManageSimple.new
              manage_simple.manageSimple(graph, csa, option_simple, graph_parsed)#Operation simple
            end
     elsif option == 2#here with exiting the menu always after finitializiating
          option_static = manager.putOptionsStatistic
          manage_static = ManageStatic.new
          manage_static.manageStatistic(graph,option_static, csa,graph_parsed)#operation static
        elsif option == 3 #get all fligths in reach
          ManageList.new.getAllFligths(graph_parsed, csa)
        elsif option == 4 #return all fligth on route
          ManageRoutes.new.getRoutes(graph_parsed)
        elsif option == 5 #return all fligth on route
                option_modify  = manager.putOptionsModify
                ManageRoutesModify.new.manageRoutes(graph, option_modify, csa)
                graph_parsed = graph.parse
        elsif option == 6#Update the disk
          GraphParse.new.updateDisk(graph_parsed)
        elsif option== 7#Show the cost Set up
          COST_KM_LESS_1000,   COST_KM_MORE_1000, TIME_PER_AIR, SPEED_SHORT, SPEED_LONG =CostTimeCalculation.new.getALL()
          puts "Information about the costs"
          puts "Cost per km less than 1000 is #{COST_KM_LESS_1000}"
          puts "Cost per km more than 1000 is #{COST_KM_MORE_1000}"
          puts "Time per stop is in average  #{TIME_PER_AIR+0.5}"
          puts "Speed when the flight is less than 1000km in average is #{SPEED_SHORT}"
          puts "Speed when the flight is more than 1000km in average is #{SPEED_LONG}"
          puts "NOW play with option 8"
        
        elsif option == 8#Shortestfligth
        ManageStatistic.new.start(parsed)
        elsif option == 9 #Merges the file
          file = open("cmi_hub.json")
          new_parsed = gp.parseFile(file)
          parsed, graph_parsed, graph = gp.merge(parsed ,new_parsed)
          
      end
    end
  csa = manager.getInfo 
  puts csa
  csa.delete!("\n")
  option = 2
end




