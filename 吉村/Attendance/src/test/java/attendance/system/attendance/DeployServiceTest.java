package attendance.system.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import attendance.system.attendance.model.Deploy;
import attendance.system.attendance.repository.DeployRepository;
import attendance.system.attendance.service.DeployService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeployServiceTest {

    @Mock
    private DeployRepository deployRepository;

    @InjectMocks
    private DeployService deployService;

    @Test
    void deployIdから部署名が取得できること() {

        // モック用 Deploy（中身は直接 new するだけ）
        Deploy deploy = new Deploy();

        // Repository の戻り値だけ定義
        when(deployRepository.findById(1)).thenReturn(deploy);

        // 実行
        Deploy result = deployService.getDeploy(1);

        // 検証（null でないことを確認）
        assertEquals(deploy, result);
    }
}