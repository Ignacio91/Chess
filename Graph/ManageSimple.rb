=begin
  * Manager of the simple operation
  * Description :Switch case of options
  * Author:Ignacio Ferrero
=end

class ManageSimple
  def manageSimple(graph, csa, option, graph_parsed)
    utilities = GraphUtilities.new(graph_parsed)
          case option
            when 1 then utilities.getOperation(csa, "code")
            when 2 then utilities.getOperation(csa, "name")
            when 3 then utilities.getOperation(csa, "country")
            when 4 then utilities.getOperation(csa, "continent")
            when 5 then utilities.getOperation(csa, "timezone")
            when 6 then utilities.getOperation(csa, "coordinates")
            when 7 then utilities.getOperation(csa, "population")
            when 8 then utilities.getOperation(csa, "region")
            when 9 then utilities.getOperation(csa, "destinations")
          end
  end
end