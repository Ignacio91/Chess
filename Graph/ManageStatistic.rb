require_relative 'Shortestpath.rb'
require_relative 'Manager.rb'
require_relative 'GraphParse.rb'
=begin
  * ManageStatistic, 
  * Description :option 8  manage the  path with lowest cost/time/km and isplays all required statistics
  * Author:Ignacio Ferrero
=end
class ManageStatistic
  def start(parsed)
    ps = GraphParse.new
    ps.parseRoutes(parsed)
    #Initlialization of the graphs with different costs
    km_cost = Shortestpath.new(ps.parse_km)
    fligth_cost = Shortestpath.new(ps.parse_fligth)
    time_cost = Shortestpath.new(ps.parse_time)
    #Gets start and stop
    start, stop = Manager.new.getPath()
    #KM
    path, dist = km_cost.DjkstraShortestPath(start, stop, "")
    puts "Shortest DISTANCE   from #{start} to #{stop}  is  :#{dist}km"
    puts path.join(" -> ")
    #Cost
    path, dist = fligth_cost.DjkstraShortestPath(start, stop, "")
    puts "Shortest COST     from #{start} to #{stop}  is  :#{dist}$$"
    puts path.join(" -> ")
    #Time
    path, dist = time_cost.DjkstraShortestPath(start, stop, "TIME")
    puts "Shortest TIME     from #{start} to #{stop}  is  :#{dist}h"
    puts path.join(" -> ")
    
    
  end
  
end