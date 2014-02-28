class GraphParse
  def create(parse)
      p parse["metros"]
      graph = []
       metros = parse["metros"]
      metros.map{ |airport|
        graph.push(airport.merge(getdestinitation(parse, airport["code"])))
      }
      p graph
      return graph
    end
=begin
      * Function gets all the destination for the grap within one fligth
=end
    def getdestinitation(parse, code)
      destination = []
      routes = parse["routes"]
      routes.map{ |airport|
        if(airport["ports"].include?(code))
          destination.push(airport)
        end
        }
       h = {"destinations"=> destination}
      return h
    end
    
    def UpdateDisk(csa)
      File.open("map-data1.json","w") do |f|
        f.write(JSON.pretty_generate(csa))
      end
    end

end