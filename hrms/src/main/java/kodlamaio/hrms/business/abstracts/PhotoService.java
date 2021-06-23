package kodlamaio.hrms.business.abstracts;

import java.util.List;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Photo;

public interface PhotoService {
	DataResult<List<Photo>> getAllPhotos();
	Result add(Photo photo,int candidateId);
	Result delete(int id);
	DataResult<Photo> getById(int id);
	Result deletePhotoFromCv(int candidateId, int photoId);
	Result addCandidatePhoto(Photo photo, int candidateId);

}
