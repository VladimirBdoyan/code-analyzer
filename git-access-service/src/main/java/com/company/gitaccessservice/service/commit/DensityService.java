package com.company.gitaccessservice.service.commit;

import com.company.gitaccessservice.dto.commit.GitAccessCommitDetailsDto;
import com.company.gitaccessservice.dto.commit.RequestDto;

public interface DensityService {
    GitAccessCommitDetailsDto getCommitDensityByOrg(RequestDto request);
    GitAccessCommitDetailsDto getCommitDensityByOrgRepo(RequestDto request);
    GitAccessCommitDetailsDto getCommitDensityByUserRepo(RequestDto request);
    GitAccessCommitDetailsDto getCommitDensityByUser(RequestDto request);
}
