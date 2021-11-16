package com.company.gitaccessservice.service.commit;

import com.company.gitaccessservice.dto.commit.GitAccessCommitDetailsDto;
import com.company.gitaccessservice.dto.commit.RequestDto;

public interface DensityService {
    GitAccessCommitDetailsDto getCommitDensityByOrg(RequestDto request);
    GitAccessCommitDetailsDto getCommitDensityByRepo(RequestDto request);
}
