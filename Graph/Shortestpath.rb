=begin
  * DJKSTRA ALGORITHM
  * Description :Calculate the shortest path within the graph depends on the calculate cost
                 For Time : Every fligth add
=end

class Shortestpath
  #Variable to penalyze every layover/stops
  TIME_PER_AIR = 2
  TIME_AVG_DELAY = 0.5
  TOTAL = TIME_AVG_DELAY + TIME_PER_AIR
  
  Vertex = Struct.new(:name, :neighbours, :dist, :prev)
  
=begin
    * Initialized the graph with the related costs every time merge is updated
=end
  def initialize(graph)
      @vertices = Hash.new{|h,k| h[k]=Vertex.new(k,[],Float::INFINITY)}
      @edges = {}
      graph.each do |(v1, v2, dist)|
        @vertices[v1].neighbours << v2
        @vertices[v2].neighbours << v1
        @edges[[v1, v2]] = @edges[[v2, v1]] = dist#vertexes are non directional
      end
      @dijkstra_source = nil
    end
=begin
  * Seached for the chortest route in an efficient way with all the cost related
=end
  def dijkstra(source, operation)
      return  if @dijkstra_source == source
      vertex_cost = @vertices.values#initialize all the costs
      vertex_cost.each do |v|
        #initializes vertex to infinity
        v.dist = Float::INFINITY
        v.prev = nil
      end
      @vertices[source].dist = 0
      until vertex_cost.empty?# no more vertexes to search
        u = vertex_cost.min_by {|vertex| vertex.dist}
        break if u.dist == Float::INFINITY
        vertex_cost.delete(u)
        u.neighbours.each do |v|
          vertex_path = @vertices[v]#always save the best path
          if vertex_cost.include?(vertex_path)
            alt = u.dist + @edges[[u.name, v]]
              if(operation == "TIME")#if the operation is Time there is a stop between fligths --> adjust
                if alt+TOTAL < vertex_path.dist
                  vertex_path.dist = alt+TOTAL
                  vertex_path.prev = u.name
                end
              else
                if alt < vertex_path.dist
                  vertex_path.dist = alt
                  vertex_path.prev = u.name
                end
              end
          end
        end
      end
      @dijkstra_source = source
    end
=begin
  * Finds the shortest path available, djkstra return all the path , and the algorithm keeps the best one
    * and returns it
=end   
    def DjkstraShortestPath(source, target, operation)
      dijkstra(source, operation)
      path = []
      u = target
      while u
        path.unshift(u)
        u = @vertices[u].prev
      end
      return path, @vertices[target].dist
    end
end