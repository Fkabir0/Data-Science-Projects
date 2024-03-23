import pandas as pd
import matplotlib.pyplot as plt


#Read CSV file and return the data frame
def read_csv(file_path):
    try:
        df = pd.read_csv(file_path)
        return df
    except FileNotFoundError:
        print("No File Found.")
        return None
    except Exception as e:
        print("An error occurred:", e)
        return None
    

#identify state with highest average minimum wage
def highest_mean_min_wage(df):
    if df is not None:
        #Calculate average minimum wage for each state
        state_mean_wages = df.groupby('State')['EffectiveMinimumWage'].mean()
        #Identify state with highest mean minimum wage
        highest_mean_state = state_mean_wages.idxmax()
        return highest_mean_state
    else:
        return None

#plotting the graph
def plot_highest_mean_state(df, state):
    if df is not None:
        #Filtering data for the state with highest average minimum wage
        state_data = df[df['State'] == state]
        #creating the graph
        plt.plot(state_data['Year'], state_data['EffectiveMinimumWage'])
        plt.title(f"Minimum Wage Over the Years in {state}")
        plt.xlabel("Year")
        plt.ylabel("Effective Minimum Wage")
        plt.grid(True)
        plt.show()


def main():
    #Input file path 
    file_path = input("Enter file path of CSV file: ")
  
    min_wage_df = read_csv(file_path)
    if min_wage_df is not None:
        highest_mean_state = highest_mean_min_wage(min_wage_df)
        print(f"The state with the highest mean minimum wage is: {highest_mean_state}")

        #Ploting the highest average minimum wage state
        plot_highest_mean_state(min_wage_df, highest_mean_state)

if __name__ == "__main__":
    main()
