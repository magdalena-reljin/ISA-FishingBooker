package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.NewComplaintDto;
import rs.ac.uns.ftn.isa.fisherman.repository.*;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.ComplaintService;

import java.time.LocalDateTime;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private CabinReservationRepository cabinReservationRepository;
    @Autowired
    private BoatReservationRepository boatReservationRepository;
    @Autowired
    private AdventureReservationRepository adventureReservationRepository;
    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private CabinComplaintRepository cabinComplaintRepository;
    @Autowired
    private CabinOwnerComplaintRepository cabinOwnerComplaintRepository;
    @Autowired
    private BoatComplaintRepository boatComplaintRepository;
    @Autowired
    private BoatOwnerComplaintRepository boatOwnerComplaintRepository;
    @Autowired
    private FishingInstructorComplaintRepository fishingInstructorComplaintRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CabinOwnerService cabinOwnerService;

    @Override
    public boolean addComplaint(NewComplaintDto newComplaintDto) {
        if(!checkIfComplaintIsPossible(newComplaintDto)){
            return false;
        }
        saveComplaint(newComplaintDto);
        return true;
    }
    private boolean checkIfComplaintIsPossible(NewComplaintDto newComplaintDto){
        if(newComplaintDto.getSubjectRole().equals("cabin")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
            return cabinReservationRepository.clientHasReservationInCabin(cabinReservation.getCabin().getId(), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("cabin owner")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
            CabinOwner cabinOwner = cabinReservation.getCabin().getCabinOwner();
            return cabinReservationRepository.clientHasReservationInOwnersCabin(cabinRepository.findCabinsIdByOwnersId(cabinOwnerService.findByUsername(cabinOwner.getUsername()).getId()), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("boat")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            return boatReservationRepository.clientHasReservationInBoat(boatReservation.getBoat().getId(), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("boat owner")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            BoatOwner boatOwner = boatReservation.getBoat().getBoatOwner();
            return boatReservationRepository.clientHasReservationInOwnersBoat(boatRepository.findBoatsIdByOwnersId(boatOwner.getId()), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("fishing instructor")){
            AdventureReservation adventureReservation = adventureReservationRepository.getById(newComplaintDto.getReservationId());
            FishingInstructor fishingInstructor = adventureReservation.getFishingInstructor();
            return adventureReservationRepository.clientHasReservationWithInstructor(fishingInstructor.getId(), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }
        return false;
    }
    private void saveComplaint(NewComplaintDto newComplaintDto){
        if(newComplaintDto.getSubjectRole().equals("cabin")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
           cabinComplaintRepository.save(new CabinComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()), cabinReservation.getCabin()));
        }else if(newComplaintDto.getSubjectRole().equals("cabin owner")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
           cabinOwnerComplaintRepository.save(new CabinOwnerComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()), cabinReservation.getCabin().getCabinOwner()));
        }else if(newComplaintDto.getSubjectRole().equals("boat")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            boatComplaintRepository.save(new BoatComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()), boatReservation.getBoat()));
        }else if(newComplaintDto.getSubjectRole().equals("boat owner")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            boatOwnerComplaintRepository.save(new BoatOwnerComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()), boatReservation.getBoat().getBoatOwner()));
        }
        else if(newComplaintDto.getSubjectRole().equals("fishing instructor")){
            AdventureReservation adventureReservation = adventureReservationRepository.getById(newComplaintDto.getReservationId());
            fishingInstructorComplaintRepository.save(new FishingInstructorComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()), adventureReservation.getFishingInstructor()));
        }
    }
}
