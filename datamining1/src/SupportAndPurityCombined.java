import java.util.ArrayList;


public class SupportAndPurityCombined 
{
	float combine;
	String pattern;
	float purity;
	
	SupportAndPurityCombined(float _combine, String _pattern, float _purity)
	{
		combine=_combine;
		pattern=_pattern;
		purity=_purity;
	}

	public float getCombine()
	{
		return combine;
	}
	public String getPattern()
	{
		return pattern;
	}
	public float getPurity()
	{
		return purity;
	}
}
