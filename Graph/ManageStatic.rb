=begin
  * ManageStaticoption of the satic switch case
  * Author:Ignacio Ferrero
=end
class ManageStatic
  def manageStatistic(graph, option, csa, graph_parsed)
    
      case option
        when 1 then graph.getLongFlight(csa, graph_parsed)
        when 2 then graph.getShortFlight(csa, graph_parsed)
        when 3 then graph.getAverageFligth(csa, graph_parsed)
        when 4 then graph.getMaxPopulation(csa, graph_parsed)
        when 5 then graph.getMinPopulation(csa, graph_parsed)
        when 6 then graph.getAveragePoulation(csa, graph_parsed)
        when 7 then graph.getPopulation(csa, graph_parsed)
        when 8 then graph.getContinents(csa, graph_parsed)
        when 9 then graph.getHubs(csa, graph_parsed)
      end


  end
end