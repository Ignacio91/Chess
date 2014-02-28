class GraphUtilities
  @@parse = {}
    def initialize(graph_parse)
      @@parse = graph_parse
    end
=begin
        * Standart Function for Simple request shows the information you 
         * requested from an airport all operation included
=end
    def getOperation(csa, operation)
      puts "CSAIR Codes of" 
      @@parse.map{ |airport| 
        if( airport["name"] == csa) 
          puts "The "+ operation +" is " +airport[operation].to_s
        end
    }
  end
    def getCities(csa)
        puts "CSAIR Codes of" + csa
        graph.map{ |airport| 
          puts
        }
  
        
    end
=begin
       * Gets the longest fligth this city operates to
=end
   def getLongFlight(csa)
     number2 = 0
     @@parse.map{ |airport| 
           if( airport["name"] == csa) 
             airport["destinations"].map do |element|
                number1 = element["distance"]
                  if(number1>number2)
                    number2 = number1
                  end
             end
             airport["destinations"].map{ |dest| 
               if(number2 == dest["distance"])
                 puts "The max distance is  " + number2.to_s + " in the fligth between "+ dest["ports"].to_s
               end
             }
           end
     }
   end
=begin
       * Gets the shortes fligth this city operates
=end
 def getShortFlight(csa)
     number2 = 10000000
   @@parse.map{ |airport| 
           if( airport["name"] == csa) 
             airport["destinations"].map do |element|
                number1 = element["distance"]
                  if(number1<number2)
                    number2 = number1
                  end
             end
             airport["destinations"].map{ |dest| 
               if(number2==dest["distance"])
                 puts "The min distance is  " + number2.to_s + " in the fligth between "+ dest["ports"].to_s
               end
             }
           end
     }
   end
=begin
       * Gets the avergae distance of all flight this city operates
=end
 def getAverageFligth(csa, parse)
   number1=0
   n=0
   @@parse.map{ |airport| 
           if( airport["name"] == csa) #found the name
             airport["destinations"].map do |element|
               number1 += element["distance"].to_i
                 n+=1
             end
             puts "The average Distance from "+ csa +  " fligths is : " + (number1/n).to_s
           end
             
     }
   end
=begin
       * Gets the max populatiomn from this city within one fligth
=end
 def getMaxPopulation(csa)
   population1 = 0
   @@parse.map{ |airport| 
           if( airport["name"] == csa) 
             airport["destinations"].map do |element,i|
               code = getCode(element["ports"], airport["code"])
               population2 = getPopulation(code, graph) 
               if(population2>population1)
                 population1 = population2
               end
             end
             
             puts "The city with Max population from "+ csa +" available  is : " + getPopulationCity(population1, graph)
           end
             
     }
   end
=begin
         * Gets the longest fligth this city operates to
=end
 def getAveragePoulation(csa, parse)
   population1 = 0
   n = 0
   @@parse.map{ |airport| 
           if( airport["name"] == csa) 
             airport["destinations"].map do |element|
               code = getCode(element["ports"], airport["code"])
               population1 += getPopulation(code, graph).to_i 
               n+=1
               end
             puts "The average population from "+ csa +  " fligths is : " + (population1/n).to_s
             end
             
             
     }
   end
=begin
         * Gets the longest fligth this city operates to
=end
 def getMinPopulation(csa, parse)
   population1 = 10000000000
   @@parse.map{ |airport| 
           if( airport["name"] == csa) 
             airport["destinations"].map do |element,i|
               code = getCode(element["ports"], airport["code"])
               population2 = getPopulation(code, graph) 
               if(population2<population1)
                 population1 = population2
               end
             end
             
             puts "The City with Min population is : " + getPopulationCity(population1, graph)
           end
             
     }
   end
=begin
         * Gets the longest fligth this city operates to
=end
   def getPopulation(code, parse)
     @@parse.map{ |airport| 
               if( airport["code"] == code) 
                 return airport["population"]
               end
     }
   end
=begin
         * Gets the longest fligth this city operates to
=end
    def getPopulationCity(code, parse)
      @@parse.map{ |airport| 
                   if( airport["population"] == code) 
                     return airport["name"]
                   end
         }
   end
   
   def getCode(code1, code2)
     if(code1[1] == code2)
       return code1[0]
     else
        return code1[1]
       end   
   end
   
   def getListofContinents(csa)
     end
   def getHubCity(csa)
     return 2
   end
end