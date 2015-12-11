package cz.fi.muni.pa165.dogbarbershop_sampledata;

/**
 *
 * @author Severin Simko
 */
import java.io.IOException;

/**
 * Populates database with sample data.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public interface SampleDataLoadingFacade {

    void loadData() throws IOException;
}