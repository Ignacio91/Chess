=begin
  * ManageStaticoption of the satic switch case
  * Author:Ignacio Ferrero
=end
class ManageStatic
  def manageStatistic(graph, option, csa, graph_parsed)
    
      case option
        when 1 then graph.getLongFlight(csa, graph_parsed)
        when 2 then graph.getShortFlight(csa, graph_parsed)
        when 3 then graph.getAverage(csa, graph_parsed)
        when 4 then graph.getContinent(csa, graph_parsed)
        when 5 then graph.getTimeZone(csa, graph_parsed)
        when 6 then graph.getLatitude(csa, graph_parsed)
        when 7 then graph.getPopulation(csa, graph_parsed)
        when 8 then graph.getRegion(csa, graph_parsed)
        when 9 then graph.getCities(csa, graph_parsed)
      end


  end
end