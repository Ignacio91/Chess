=begin
  * ManageRoutes, prints all possible Routes for future assignements make interactive Ruby on rails
  * Description :Shows all the routes
  * Author:Ignacio Ferrero
=end
#require 'net/http'
class ManageRoutes
  def getRoutes(graph)
    arrayRoutes=[]
    graph.map{ |airport| 
           airport["destinations"].map do |element|
             arrayRoutes.push(element["ports"][0] +"-" + element["ports"][1])
                  end
       puts arrayRoutes
      #sendUrl(routes) 
    }
    
  end
  def sendUrl(routes)
    uri = URI('http://www.gcmap.com/')
    Net::HTTP.get(uri) # => results
  end
end