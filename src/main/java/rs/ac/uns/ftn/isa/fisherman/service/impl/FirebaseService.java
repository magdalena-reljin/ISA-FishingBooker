package rs.ac.uns.ftn.isa.fisherman.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Photo;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public String savePhoto(Photo photo) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =
                dbFirestore.collection("./pictures").document(photo.getTitle()).set(photo);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}
