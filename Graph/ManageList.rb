=begin
  * ManageList list all the possible path within reach
  * Description :Loops within the graph
  * Author:Ignacio Ferrero
=end
class ManageList
  def getAllFligths(graph, csa)
    graph.map{ |airport| 
       if( airport["name"] == csa) 
            iterateRecursive(airport["destinations"], graph, csa)            
        end
    }
    end
    def iterateRecursive(ports, graph, csa)
=begin ports.map{ |airport| 
        #findAlldestinies()
        
      }
=end
      puts "Connections from  "+ csa +" are : "
      graph.map{ |airport| 
        puts airport["name"]
      }
    end

end