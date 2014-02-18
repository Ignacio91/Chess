package classification;

import java.util.ArrayList;


public class Ada {

		float D ;
		Label label;
		int classifier;
		float bt;
		
		Ada(float _D, Label _label)
		{
			label = _label;
			D = _D;
		}
		Ada(float _D, Label _label, int _classifier, float _bt)
		{
			label = _label;
			D = _D;
			classifier =_classifier;
			bt = _bt;
		}
		
		public Label getLabel(){
			return label;
		}
		public float getD(){
			return D;
		}
		public float getBT(){
			return bt;
		}
		public void setClassifier(int Classifier)
		{
			classifier = Classifier;
		}
		public int getClassifier(){
			return classifier;
		}

	}


