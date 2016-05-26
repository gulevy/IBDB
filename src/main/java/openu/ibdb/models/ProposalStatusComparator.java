package openu.ibdb.models;

import java.util.Comparator;

public class ProposalStatusComparator implements Comparator<ProposalState> {

	@Override
	public int compare(ProposalState o1, ProposalState o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}

}
