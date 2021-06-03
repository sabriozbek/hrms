package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.PhotoDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.Photo;
import net.bytebuddy.asm.Advice.This;
@Service
public class PhotoManager implements PhotoService{

	private PhotoDao photoDao;
	private CandidateDao candidateDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	public PhotoManager(PhotoDao photoDao, CandidateDao candidateDao,
			CurriculumVitaeDao curriculumVitaeDao) {
		this.photoDao = photoDao;
		this.candidateDao = candidateDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public DataResult<List<Photo>> getAllPhotos() {
		return new SuccessDataResult<List<Photo>>(this.photoDao.findAll());
	}

	@Override
	public Result add(Photo photo, int candidateId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		photo.setCandidate(candidate);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		photo.setCurriculumVitae(curriculumVitae);		
		this.photoDao.save(photo);
		return new SuccessResult("Fotoğraf kaydedilmiştir.");
		
	}

	@Override
	public Result delete(int id) {
		this.photoDao.deleteById(id);
		return new SuccessResult("Fotoğraf başarıyla silinmiştir.");
	}

	@Override
	public DataResult<Photo> getById(int id) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<Photo>(this.photoDao.getById(id));
	}

}
