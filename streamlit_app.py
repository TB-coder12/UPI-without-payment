import streamlit as st
import pandas as pd

st.set_page_config(page_title="UPI Payment Simulator", page_icon="💳")

if "users" not in st.session_state:
    st.session_state.users = {
        "ram@upi": {"name": "Ram", "balance": 5000},
        "shyam@upi": {"name": "Shyam", "balance": 2000},
        "rita@upi": {"name": "Rita", "balance": 3000}
    }

if "transactions" not in st.session_state:
    st.session_state.transactions = []

st.title("💳 UPI Payment Simulator")
st.write("Demo project - No real payments involved")

menu = st.sidebar.selectbox(
    "Select Option",
    ["Check Balance", "Send Money", "Transaction History"]
)

if menu == "Check Balance":
    st.header("Check Balance")

    upi_id = st.selectbox(
        "Select UPI ID",
        list(st.session_state.users.keys())
    )

    if st.button("Show Balance"):
        balance = st.session_state.users[upi_id]["balance"]
        st.success(f"Balance: ₹{balance}")

elif menu == "Send Money":
    st.header("Send Money")

    sender = st.selectbox(
        "Sender",
        list(st.session_state.users.keys())
    )

    receiver = st.selectbox(
        "Receiver",
        list(st.session_state.users.keys())
    )

    amount = st.number_input(
        "Amount",
        min_value=1.0,
        step=1.0
    )

    if st.button("Transfer"):
        if sender == receiver:
            st.error("Sender and Receiver cannot be same")
        else:
            sender_balance = st.session_state.users[sender]["balance"]

            if sender_balance >= amount:
                st.session_state.users[sender]["balance"] -= amount
                st.session_state.users[receiver]["balance"] += amount

                st.session_state.transactions.append({
                    "Sender": sender,
                    "Receiver": receiver,
                    "Amount": amount
                })

                st.success("Payment Successful ✅")
            else:
                st.error("Insufficient Balance ❌")

elif menu == "Transaction History":
    st.header("Transaction History")

    if len(st.session_state.transactions) == 0:
        st.info("No transactions found")
    else:
        df = pd.DataFrame(st.session_state.transactions)
        st.dataframe(df, use_container_width=True)
