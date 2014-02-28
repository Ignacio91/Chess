=begin
  * ManageStaticoption of the satic switch case
  * Author:Ignacio Ferrero
=end
require_relative 'Graph.rb'
require_relative 'GraphUtilities.rb'
class ManageStatic
  def manageStatistic(graph, option, csa, graph_parsed)
    utilities = GraphUtilities.new(graph_parsed)
      case option
        when 1 then utilities.getLongFlight(csa)
        when 2 then utilities.getShortFlight(csa)
        when 3 then utilities.getAverageFligth(csa)
        when 4 then utilities.getMaxPopulation(csa)
        when 5 then utilities.getMinPopulation(csa)
        when 6 then utilities.getAveragePoulation(csa)
        when 7 then utilities.getPopulation(csa)
        when 8 then utilities.getContinents(csa)
        when 9 then utilities.getHubs(csa)
      end


  end
end