=begin
  * Manager of the simple operation
  * Description :Switch case of options
  * Author:Ignacio Ferrero
=end
class ManageSimple
  def manageSimple(graph, csa, option, graph_parsed)
          
          case option
            when 1 then graph.getOperation(csa, "code", graph_parsed)
            when 2 then graph.getOperation(csa, "name", graph_parsed)
            when 3 then graph.getOperation(csa, "country", graph_parsed)
            when 4 then graph.getOperation(csa, "continent", graph_parsed)
            when 5 then graph.getOperation(csa, "timezone", graph_parsed)
            when 6 then graph.getOperation(csa, "coordinates", graph_parsed)
            when 7 then graph.getOperation(csa, "population", graph_parsed)
            when 8 then graph.getOperation(csa, "region", graph_parsed)
            when 9 then graph.getOperation(csa, "destinations", graph_parsed)
          end
  end
end