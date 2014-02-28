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
require_relative 'GraphParse.rb'

manager = Manager.new
manager.welcome
#opens the json file
file = open("map_data.json")
puts file
json = file.read
parsed = JSON.parse(json)
#parse the json into a grpah / hash
#graph = Graph.new()
graph_parsed =GraphParse.new.create(parsed)
graph= Graph.new(graph_parsed)


csa = manager.getInfo
csa.delete!("\n")
puts csa
option = 2
until csa == "X"#Exit option
    until option == 5#Exit option
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
                ManageRoutesModify.new.Routes(graph_parsed)
      elsif option == 6
        graph_parsed.UpdateDisk(graph_parsed)
        end
    end
  csa = manager.getInfo 
  puts csa
  csa.delete!("\n")
  option = 2
end





