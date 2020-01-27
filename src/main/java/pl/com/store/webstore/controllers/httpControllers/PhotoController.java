package pl.com.store.webstore.controllers.httpControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.photoService.FileChooser;

@Controller
public class PhotoController {



    @GetMapping("/photos")
    public String getPhotoPath(Model model){
        String[] arguments = new String[]{};
        FileChooser.main(arguments);
        String pathFromChooser = FileChooser.getPath();
        model.addAttribute("photolocation",  pathFromChooser);
        return "/additem";
    }
}
