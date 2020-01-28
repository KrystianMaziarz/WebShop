package pl.com.store.webstore.controllers.httpControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.store.webstore.photoService.FileChooser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/photos")
public class PhotoController {


    @GetMapping
    public void getPhotoPath(HttpServletRequest request, HttpServletResponse response) {
        String[] arguments = new String[]{};
        FileChooser.main(arguments);
        String pathFromChooser = FileChooser.getPath();
        String url = "wellcome/admin/additem/withPhotoUrl";
        request.getSession().setAttribute("photoUrl", pathFromChooser);
        response.setHeader("Location", url);
        response.setStatus(302);
    }
}
