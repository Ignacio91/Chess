=begin
  * ManageRoutes, prints all possible Routes for future assignements make interactive Ruby on rails
  * Description :Shows all the routes
  * Author:Ignacio Ferrero
=end
require 'net/http'
class ManageRoutes
  def getRoutes(graph)
    arrayRoutes=[]
    graph.map{ |airport| 
           airport["destinations"].map do |element|
             arrayRoutes.push(element["ports"][0] +"-" + element["ports"][1])
                  end
       #puts arrayRoutes
      
    }
  sendUrl(arrayRoutes) 
    
  end
  def sendUrl(routes)
    #puts routes
    routes = routes.uniq
    puts routes
    uri = ""
    #routes.gsub!('\n',',')
    routes.map{|routes, i|
      uri += routes.to_s + ","
    }
    puts uri
    uri_final ='http://www.gcmap.com/mapui?P=' + uri
    system("open -a Safari #{uri_final}")
    #uri = URI('http://www.gcmap.com/')
    #Net::HTTP.get(uri) # => results
  end
end