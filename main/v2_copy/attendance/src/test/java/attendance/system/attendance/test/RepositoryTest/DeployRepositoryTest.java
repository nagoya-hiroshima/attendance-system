package attendance.system.attendance.test.RepositoryTest;


import org.springframework.boot.test.context.SpringBootTest;

import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.repository.DeployRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

@SpringBootTest
class DeployRepositoryTest {

    @Autowired
    private DeployRepository DeployRepository;

    @Test
    void testBusho() {
        Deploy Deploy = new Deploy(1, "営業部");
        DeployRepository.save(Deploy);

        Deploy result = DeployRepository.findById(1).orElse(null);

        System.out.println(result);
    }
    
}
