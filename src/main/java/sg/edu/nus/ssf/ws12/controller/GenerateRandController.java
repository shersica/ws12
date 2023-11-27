package sg.edu.nus.ssf.ws12.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.ssf.ws12.exception.RandNoException;
import sg.edu.nus.ssf.ws12.model.Generate;

@Controller
@RequestMapping("/rand")
public class GenerateRandController {
    
    @GetMapping("/show")
    public String showRandomForm(Model model){
        Generate g = new Generate();
        model.addAttribute("generatedObj", g);
        return "generate";
    }

    @GetMapping("/generate")
    public String generate(@RequestParam int numberVal, Model model){   
        this.randomizeNumber(model, numberVal);
        return "result";
    }

    public void randomizeNumber(Model model, int noOfGenerateNo){
        int maxGenNo = 31;
        Random rand = new Random();
        int randNumber;
        Set<String> selectedNumbers = new HashSet<>();
        int generatedNo = noOfGenerateNo;

        if(generatedNo <= 0 || generatedNo > maxGenNo - 1){
            throw new RandNoException();
        }
        while (generatedNo != 0) {
            randNumber = rand.nextInt(maxGenNo);
            if(randNumber == 0){
                continue;
            } else{
                if(!selectedNumbers.contains(String.valueOf(randNumber))){
                    selectedNumbers.add(String.valueOf(randNumber));
                    generatedNo --;
                } else {
                    continue;
                }
            }
        }

        List<String> selectedImgs = new ArrayList<>();
        for(String number : selectedNumbers){
            StringBuilder str = new StringBuilder("number");
            selectedImgs.add(str.append(number + ".jpg").toString());
        }

        model.addAttribute("noOfGenerateNo", noOfGenerateNo);
        // model.addAttribute("generatedNo", generatedNo);
        model.addAttribute("selectedImgs", selectedImgs);
        System.out.println(">>> " + selectedImgs);

    }

    // Prof's code

    //     int maxGenNo = 31;
    //     String[] imgNumbers = new String[maxGenNo];

    //     if(noOfGenerateNo < 1 || noOfGenerateNo > maxGenNo -1){
    //         throw new RandNoException();
    //     }

    //     for(int x = 0; x < maxGenNo; x++){
    //         if(x > 0){
    //             System.out.println("x> " + x);
    //             imgNumbers[x] = "number" + x + ".jpg";
    //         }
    //     }

    //     List<String> selectedImgs = new ArrayList<String>();
    //     Random rand = new Random();
    //     Set<Integer> uniqueResult = new LinkedHashSet<Integer>();
    //     while(uniqueResult.size() < noOfGenerateNo){
    //         Integer randNoResult = rand.nextInt(maxGenNo);
    //         if(randNoResult != null){
    //             if(randNoResult > 0)
    //                 uniqueResult.add(randNoResult);
    //         }
    //     }

    //     Integer currElem= null;
    //     for(Iterator iter = uniqueResult.iterator(); iter.hasNext();){
    //         currElem = (Integer)iter.next();
    //         System.out.println(currElem);
    //         if(currElem != null)
    //             selectedImgs.add(imgNumbers[currElem]);
    //     }

    //     m.addAttribute("noOfGenerateNo", noOfGenerateNo);
    //     m.addAttribute("selectedImgs", selectedImgs);
    //     System.out.println(" >>> " + selectedImgs);
    // }


}
