class ManageRoutesModify
    def manageRoutes(graph, option, csa)
            
            case option
              when 1 then graph.addNode()
              when 2 then graph.addRoute(csa)
              when 3 then graph.deleteNode(csa)
              when 4 then graph.deleteRoute(csa)
              when 5 then graph.editCity(csa, getParameter, getValue)
              
            end
    end
    def getParameter
      puts "What parameter do you want to modify (remember Continent and coordinates are not available the earth does not change)"
       return gets.delete!("\n")
    end
    def getValue
      puts "And the new Value is (remember restrictions) "
      return gets.delete!("\n")
    end
end