=begin
  * Manager
  * Description :Handle of the inputs/outputs(MENUS) of the program  
  * Author:Ignacio Ferrero
=end
require "rubygems"
require "json"

class Manager
  #constructor
  def initialize()
    end
    def welcome
      puts 'Welcome to the Programming Assigment 2'
    end
  #get the city you want to look at
    def getInfo   
      puts 'Enter your CSA :'
      puts 'Option "X": for escape'
      csa = gets
      
      return csa
    end
  #Main Menu to look what you want to do
    def getRealInformation(csa)
      puts 'Information from CSA'
      puts 'Option 1: Go to Simple Information'
      puts 'Option 2: Go to statistic Information'
      puts 'Option 3: List all of the cities available'
      puts 'Option 4: Get All Routes'
      puts 'Option 5: Modify Routes'
      puts 'Option 6: Save Information'
      puts 'Option 7: Route Information'
      puts 'Option 8: Shortest Route'
      puts 'Option 9: Merge File'
      puts 'Option 10: Escape'
  
      option = gets
      option = checkEndCon(option.to_i, 1,10)
      
      return option
     
      end
  #Available info from the city
  def putOptionsSimple
        puts 'Option 1: Print code'
        puts 'Option 2: Print name'
        puts 'Option 3: Print country'
        puts 'Option 4: Print continent'
        puts 'Option 5: Print Timezone'
        puts 'Option 6: Print Latituted and Longitude'
        puts 'Option 7: Print population'
        puts 'Option 8: Print region'
        puts 'Option 9: List cities within reach'
        puts 'Option 10: Escape'
        
        option = gets
        option = checkEndCon(option.to_i, 1, 10)
        return option
      end
  def putOptionsModify
          puts 'Option 1: Add city'
          puts 'Option 2: Add a Route'
          puts 'Option 3: Delete a City'
          puts 'Option 4: Delete a Route'
          puts 'Option 5: Edit city'
          puts 'Option 6: Exit'
          
          option = gets
          option = checkEndCon(option.to_i, 1, 6)
          return option
        end
  #Function to look at if the input of the user is valid
    def checkEndCon(option, min, max)
      until checkcondition(min,max, option)
          puts 'Invalid Option try again'
          option = gets
          option= option.to_i
        end
        
        return option
    end
  

  
    
   #checks if it is within the limits
    def checkcondition(min, max, num)
      if(min<=num and max >= num)
        return true
      else
        return false
      end
    end
  
    def putOptionsStatistic
      puts 'Option 1: Longest single flight'
      puts 'Option 2: Shortest single flight'
      puts 'Option 3: Average of distance of all flights'
      puts 'Option 4: Biggest city  population within reach'
      puts 'Option 5: Smallest city  population within reach'
      puts 'Option 6: Average city  population within reach'
      puts 'Option 7: List of continents and cities'
      puts 'Option 8: Identify Csa Hub cities'
      puts 'Option 9: Escape'
      
      option = gets
      option = checkEndCon(option.to_i, 1, 9)
      return option
    end
=begin
           * Gets the path for the shortest algorithm
=end
 def getPath
   puts "Introduce Source Code"
   start = gets.delete!("\n")
   puts "Introduce end Code"
   finish = gets.delete!("\n")
   return start, finish
 end
=begin
           * Gets the rout menu
=end
  def getRoutMenu
    puts 'Option 1: Add Route'
    puts 'Option 2: Exit'
    
    option = gets
    option = checkEndCon(option.to_i, 1, 2)
    
    return option.to_i
  end
  

 
end