package br.ufc.nuvem.patrimoniomanager.util;

import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.S3Actions;

import javax.swing.plaf.nimbus.State;
import java.util.List;
import java.util.UUID;

public class BucketUtils {
    public static String createPolicyJson(String bucketName) {
        Statement statement = new Statement(Statement.Effect.Allow)
                .withPrincipals(Principal.AllUsers)
                .withActions(S3Actions.GetObject)
                .withResources(
                        new Resource("arn:aws:s3:::" + bucketName + "/*")
                );
        return new Policy(UUID.randomUUID().toString(), List.of(statement)).toJson();
    }
}
