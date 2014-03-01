=begin
  * Tests for Djkstra Algorithm , with unrelated simple problem
=end
require "test/unit"
require_relative "Shortestpath.rb"
class TestShortest
  short = ShortestPath.new([ [:a, :b, 9],
                  [:a, :c, 11],
                  [:a, :f, 16],
                  [:b, :c, 12],
                  [:b, :g, 17],
                  [:c, :g, 13],
                  [:c, :f, 4],
                  [:g, :e, 8],
                  [:e, :f, 11],
                ])
   
  start, stop = :a, :e
  path, dist = short.shortest_path(start, stop)
  assert_equal(20, dist)
  puts "shortest path from #{start} to #{stop} has cost #{dist}:"
  puts path.join(" -> ")
  assert_equal("a -> c -> f -> e",path.join(" -> ")) 
  
  
end