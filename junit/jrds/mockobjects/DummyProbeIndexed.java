package jrds.mockobjects;

import jrds.Probe;
import jrds.probe.IndexedProbe;

public class DummyProbeIndexed extends DummyProbe implements IndexedProbe {
	Class<? extends Probe<?,?>> originalProbe;

	public void configure(Class<? extends Probe<?,?>> originalProbe) {
		super.configure(originalProbe);
	}

	public String getIndexName() {
		return "EmptyIndex";
	}

	public String getLabel() {
		return "EmptyLabel";
	}

	public void setLabel(String label) {
	}
}
