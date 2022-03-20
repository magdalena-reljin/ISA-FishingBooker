package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.UserTokenState;

public interface LoginService {
    UserTokenState LogIn(String username,String password);

}
