=begin
  * ManageList list all the possible path within reach
  * Description :Loops within the graph
  * Author:Ignacio Ferrero
=end
class ManageList
  def getAllFligths(graph, csa)
    graph.map{ |airport| 
       if( airport["csa"] == code) 
            iterateRecursive(airport["destinations"], graph)            
        end
    }
    end
    def iterateRecursive(ports, graph, csa)
      ports.map{ |airport| 
        #findAlldestinies()
        
      }
      graph.map{ |airport| 
        puts "Connections from"+ csa +" are"+  + airport["name"]
      }
    end

end