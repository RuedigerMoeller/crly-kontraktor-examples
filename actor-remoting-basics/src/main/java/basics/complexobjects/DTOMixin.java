package basics.complexobjects;

import org.nustaq.kontraktor.IPromise;
import org.nustaq.kontraktor.remoting.base.JsonMapped;

import static org.nustaq.kontraktor.Actors.resolve;

public interface DTOMixin {

    @JsonMapped default IPromise<SampleDTO> jsonArgMapped(@JsonMapped SampleDTO dto) {
        return resolve(dto);
    }

    default IPromise<SampleDTOAutoMapped> jsonArgAutoMapped(SampleDTOAutoMapped dto) {
        return resolve(dto);
    }

    default IPromise<SampleDTO> jsonArgDefaultSerialized(SampleDTO dto) {
        return resolve(dto);
    }

}
