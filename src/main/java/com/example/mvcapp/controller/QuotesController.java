// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuotesController {

    @GetMapping("/history")
    public String somethingFromHistory(Model model) {
        String crazyCoding = "<div style=\"margin:0;font-family:system-ui,Segoe UI,Roboto,Helvetica,Arial,sans-serif;background:#0b132b;color:#e0e6ed;display:flex;align-items:center;justify-content:center;min-height:100vh\"><main style=\"max-width:900px;padding:2rem;text-align:center;background:rgba(255,255,255,.06);border-radius:16px;box-shadow:0 10px 30px rgba(0,0,0,.3)\"><blockquote cite=\"https://www.cl.cam.ac.uk/~rja14/Papers/lovelace.pdf\" style=\"font-size:1.5rem;line-height:1.4;margin:0\">\"That brain of mine is something more than merely mortal; as time will show.\"<cite style=\"display:block;margin-top:.75rem;font-style:normal;opacity:.8\">— Ada Lovelace</cite><small style=\"display:block;margin-top:.25rem;opacity:.7\">Letter to Charles Babbage, 1843</small></blockquote></main></div>";

        model.addAttribute("FillMeUp", crazyCoding);
        return "quotes";
    }
}
