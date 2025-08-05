# FIT2099 Assignment (Semester 1, 2025)

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```

[Contribution Log](https://docs.google.com/spreadsheets/d/1G6u6llLBiDWI3PtWJMblPsftmmCcYtnYHteTBc-RqMY/edit?gid=1000079367#gid=1000079367)

- Requirement 3: The Shovel

Three new shovel items has been added to the game, where it is scattered over the valley of the inheritree where the farmer can find for it to pick it up.

The Garden Shovel(d) can be used on a grown plant surrounding the actor, the shovel can be used to remove the planted tre consuming 25 stamina, turning the 
ground back into fertile soil. This allows the farmer to manage the valley and make space for new life.

However, the valley’s may have certain magic below it, not all soil is free from corruption. When the farmer use the Ancient Shovel(z) on soil(.) 
consuming 25 stamina, there is a 50% chance where it will change the soil it is on to a blight(x). In the other hand, the farmer might have a chance with he can find a mysterious Diamond from under the soil. (Which will be implemented by my team mate Zhi Hong in his req 4)

Besides, when the Metal Shovel(a) is used against a NPC on cursed entity, it acts as a weapon, with a low chance of 25% consuming 50 stamina. 
The metal shovel has the capabilty instantly kill the NPC with a single strike.

- Requirement 4


### 💖 **Affection System: A Matter of Heart and Consequence**

In the quiet, breathing world of the valley, where merchants, mages, and warriors coexist with the whispers of the wind, the farmer’s bonds with the townsfolk are more than just passing greetings. Every act of kindness—or cruelty—leaves its mark. The Affection System governs these relationships, shaping both fate and fortune.

NOTE: - Only the farmer can give items to NPC and NPCs only receive items from the farmer. Also, All NPCs will only be affectionate to the farmer.\n
<p>      - The farmer can provide all kinds of affection to the NPCs.

#### 🎁 **Giveable Items and Their Impact**

The farmer can offer gifts to the townsfolk. But be warned—not all gifts are received equally. Some warm the heart, others turn it cold.

* **Diamond**: A gleaming symbol of value and prestige (Increases by 30 Affection)

* **Rose**: A symbol of gentleness and care (Increases by 10 Affection).

* **RottenTrash**: A foul mistake to give (Decreases by 30 Affection).


Affection ranges from **0 to 100**. A simple gift can shift a relationship—or a destiny.

NOTE: - Give is an act of kindness so it can only gift to affectionable creatures (or NPCs).
<p>      - Not all affectionate NPCs are capable of receiving gifts from the farmer. 
   
---

### 🕒 **Emotions and Their Timed Effects**

When the farmer performs an affectionate action (when the affectionate action has been executed), emotions stir. A **countdown timer** begins, and the NPC’s **emotion status** influences the world for a few turns. 
Their mood becomes more than a private feeling—it becomes a **gameplay effect** on their surroundings. In addtion, all affectionable NPCs will have an initial farmer affection level of 50.

The mood can be reset on its timer or change swiftly from one to another when receiving a gift from a farmer depending on their affection level. For example, the mood is becomes neutral instead of angry or 
happy when it reaches the netural affection level. Another example is the affectionate NPCs will have an angry mood (or status) instead of happy for several turns when their affectionate level changes from 
happy to angry levels after receiving a gift from a farmer.

#### 😄 **Positive Emotions: Happiness**

* **Sellen**, if her affection **reaches above 60**, becomes **happy** for **10 turns**. During this time, she offers all her sellable items for **free**, driven by a heart full of gratitude and generosity.

* **Kale**, once affection **hits above 70**, becomes **happy** for **5 turns**, sells any items with a deduction of 500 runes (considered free for items worth less than 500 runes). A rare smile appears behind his merchant mask.

* **Guts**, whose rough edges hide a loyal soul, becomes **happy** for **5 turns** when affection **reaches above 65**. In this state, his presence is a balm—**he heals one nearby actor for 15 HP per turn**, a quiet but profound show of kindness.


#### 😠 **Negative Emotions: Anger**

* **Guts**, however, is not all calm. When affection **drops below 30**, anger overtakes him. For **10 turns**, he becomes **angry**—and **wounds one nearby actor for 30 HP per turn**. His wrath is a storm no one wants to stand near.

* **Sellen** and **Kale**, merchants and mages of repute, never give in to rage. Anger, to them, is a poison that **drives away customers** and ruins business. No matter how wronged they are, their composure remains intact.

########Future Extensibility############

* All merchant offer runes deduction but some merchant offers it through other conditions than their statuses (e.g. Actor's current balance).
* There are other ways to provide affection to NPCs (e.g. hugs).

[Well done. Approved by Mogana and Chong (30/05/2025)]
