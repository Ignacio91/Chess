
require "test/unit"
require_relative 'Graph.rb'
class TestGraph < Test::Unit::TestCase
  def test_simple
    assert_equal(2, Graph.new.getHubCity(5))
      
  end
  def test_typecheck
      assert_raise( RuntimeError ) { Graph.new.getHubCity() }
    end
end