package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.OwnersReportDto;
import rs.ac.uns.ftn.isa.fisherman.model.OwnersReport;

public class OwnersReportMapper {
    public OwnersReport dtoToOwnersReport(OwnersReportDto ownersReportDto){
        return new OwnersReport(ownersReportDto.isBadComment(),ownersReportDto.getComment());
    }
    public OwnersReportDto ownersReportToDto(OwnersReport ownersReport){
        return new OwnersReportDto(ownersReport.isBadComment(),ownersReport.getComment());
    }
}
