=begin
  * GraphClass
  * Description :Class for calculating the existing route
  * Author:Ignacio Ferrero
=end

class CostTimeCalculation
  
  #final variables
  COST_KM_LESS_1000 = 0.25
  COST_KM_MORE_1000 = 0.1
  TIME_PER_AIR = 2
  SPEED_SHORT = 500
  SPEED_LONG = 750
=begin
    * Get all the data that you want to print
=end  
  def getALL
    return COST_KM_LESS_1000,   COST_KM_MORE_1000, TIME_PER_AIR, SPEED_SHORT, SPEED_LONG
  end
=begin
    * Gets the total cost
=end
  def getCost(distance)
    
    if(distance >1000)
      cost = 1000 * COST_KM_LESS_1000#the fist 1000 km are much more expensive
      cost += (distance-1000) * COST_KM_MORE_1000
    else
      cost = distance * COST_KM_LESS_1000
    end
      return cost
  end
  def getTime(distance)
    #get the total Time
    if(distance < 1000)
      time = distance / SPEED_SHORT
    else
      time = distance/ SPEED_LONG
    end
    
    return time
  end
end