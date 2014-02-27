require "test/unit"
require_relative 'Graph.rb'
require_relative 'Manager.rb'
class TestManager
  def test_simple
      assert_equal(true, TestManager.new.checkEndof(1, 2, 3))
      assert_equal(false, TestManager.new.checkcondition(6, 1, 3))
        
    end
end